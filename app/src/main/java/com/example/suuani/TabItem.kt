package com.example.suuani

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    var icon: Int,
    var title: String,
    var screen: ComposableFun
){
    object SANSUU : TabItem(R.drawable.sansuu, "算数", {})
    object CHUUGAKU_SUUGAKU : TabItem(R.drawable.chuugaku_suugaku, "中学数学", {})
    object KOUKOU_SUUGAKU : TabItem(R.drawable.koukou_suugaku, "高校数学", {})
    object DAIGAKU_SUUGAKU : TabItem(R.drawable.daigaku_suugaku, "大学数学", {})
}
