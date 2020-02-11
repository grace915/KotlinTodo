package com.example.kotlinstudy.main

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.kotlinstudy.R
import com.example.kotlinstudy.main.todo.TodoFragment

/**
 * Implementation of App Widget functionality.
 */
class todo_widget : AppWidgetProvider() {
    override fun onUpdate(

        //업데이트 될때 호출되는 함수? 설치될때마다래
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {

        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {

        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val widgetText = "yongwoo"
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.todo_widget)
   // views.setTextViewText(R.id.appwidget_text, widgetText)

    val intent = Intent(context, TodoFragment::class.java)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    intent.setComponent(ComponentName(context, TodoFragment::class.java))
    val pi = PendingIntent.getActivity(context, 0, intent, 0)
    views.setOnClickPendingIntent(R.id.appwidget_text, pi)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}