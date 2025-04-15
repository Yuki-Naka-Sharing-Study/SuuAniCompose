package com.example.suuani

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val tabs = listOf(
        TabItem.SANSUU,
        TabItem.CHUUGAKU_SUUGAKU,
        TabItem.KOUKOU_SUUGAKU,
        TabItem.DAIGAKU_SUUGAKU,
    )
    val pagerState = com.google.accompanist.pager.rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var pendingPage by rememberSaveable { mutableIntStateOf(0) }

    Column {
        Tabs(
            tabs = tabs,
            pagerState = pagerState,
            onTabClick = { index ->
                scope.launch { pagerState.animateScrollToPage(index) }
            }
        )
        when (pagerState.currentPage) {

        }
        TabsContent(tabs = tabs, pagerState = pagerState)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("データ喪失の可能性") },
            text = { Text("入力途中で画面遷移するとデータが喪失しますが宜しいでしょうか？") },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch { pagerState.animateScrollToPage(pendingPage) }
                    showDialog = false
                }) {
                    Text("はい")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("いいえ")
                }
            }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
private fun Tabs(
    tabs: List<TabItem>,
    pagerState: com.google.accompanist.pager.PagerState,
    onTabClick: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color(0xFF0000ff),
        contentColor = Color.Yellow,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = { /* アイコンを表示しない */ },
                text = { Text(text = tab.title, color = Color.White) },
                selected = pagerState.currentPage == index,
                onClick = { onTabClick(index) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabsContent(
    tabs: List<TabItem>,
    pagerState: com.google.accompanist.pager.PagerState
) {
    com.google.accompanist.pager.HorizontalPager(
        state = pagerState,
        count = 5,
    )
    { page ->
        tabs[page].screen()
    }
}
