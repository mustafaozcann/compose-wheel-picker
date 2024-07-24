package com.mzcnsoft.bottomwheelpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mzcnsoft.bottomwheelpicker.ui.component.BottomPicker
import com.mzcnsoft.bottomwheelpicker.ui.component.MonthPicker
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val scope = rememberCoroutineScope()
			val sheetState = rememberModalBottomSheetState(

			)
			var showDrawer by remember { mutableStateOf(false) }
			var selectedMonth by remember { mutableStateOf("April") }

			Column(
				modifier = Modifier
					.fillMaxSize()
					.background(
						color = Color(0xFF00F7AA)
					),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.SpaceEvenly
			) {
				Image(
					modifier = Modifier.size(128.dp),
					painter = painterResource(id = R.drawable.android),
					contentDescription = "",
				)
				Button(
					modifier = Modifier.drawWithCache {
						val brush = Brush.linearGradient(
							listOf(
								Color(0xFF3400CC),
								Color(0xFFBB00FF)
							)
						)
						onDrawBehind {
							drawRoundRect(
								brush,
								cornerRadius = CornerRadius(10.dp.toPx())
							)
						}
					},
					colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
					onClick = {
						scope.launch {
							sheetState.show()
						}.invokeOnCompletion {
							showDrawer = true
						}
					},
					shape = RoundedCornerShape(10.dp),
				) {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.spacedBy(8.dp)
					) {
						Icon(
							modifier = Modifier.size(24.dp),
							painter = painterResource(id = R.drawable.ic_check),
							contentDescription = null,
							tint = Color.White
						)
						Text("Show bottom picker")
					}
				}
			}
			BottomPicker(
				showDrawer = showDrawer,
				sheetState = sheetState,
				onDismissRequest = {
					showDrawer = false
				}
			) {
				MonthPicker(
					selectedMonth = selectedMonth,
					onMonthSelected = { month ->
						scope.launch {
							sheetState.hide()
						}.invokeOnCompletion {
							showDrawer = false
						}
						selectedMonth = month
					}
				)
			}
		}
	}
}
