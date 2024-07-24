package com.mzcnsoft.bottomwheelpicker.ui.component

import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.mzcnsoft.bottomwheelpicker.R

@Stable
data class BottomPickerDefaults(
	val containerColor: Color,
	val contentColor: Color,
	val dragHandleColor: Color,
	val itemTitleTextStyle: TextStyle,
	val selectionBoxBorderColor: Color,
)

@Stable
@Composable
fun pickerDefaults(): BottomPickerDefaults {
	return BottomPickerDefaults(
		containerColor = Color(0xFF0B1329),
		contentColor = contentColorFor(backgroundColor = Color(0xFF0B0F1A)),
		dragHandleColor = Color(0xFF4C5D72),
		itemTitleTextStyle = TextStyle(
			fontFamily = FontFamily(
				Font(R.font.inter)
			),
			fontSize = 32.sp,
			color = Color.White
		),
		selectionBoxBorderColor = Color.Gray
	)
}