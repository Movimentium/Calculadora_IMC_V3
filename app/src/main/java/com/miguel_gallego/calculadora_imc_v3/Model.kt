package com.miguel_gallego.calculadora_imc_v3

import android.util.Log
import java.util.Locale
import kotlin.math.pow

enum class BMICategory {
    UNDERWEIGHT,
    NORMAL_WEIGHT,
    OVERWEIGHT,
    OBESITY,
    EXTREME_OBESITY
}
 class Model(
     var weight: Float = 70f,
     var height: Float = 170f,
     var bmi: Float = 0f,
     var bmiCategory: BMICategory = BMICategory.NORMAL_WEIGHT,
     var idColor: Int = 0
) {
     init {
         Log.i("MMM", weight.toString())
         Log.i("MMM", height.toString())
         calcBMI()
     }

     fun calcBMI() {
         Log.i("MMM", "Update BMI")
         bmi = Logic.calculateBMI(weight, height)
         Log.i("MMM", bmi.toString())
         bmiCategory = Logic.bmiCategoryFrom(bmi)
         Log.i("MMM", bmiCategory.toString())
         idColor = Logic.idColorFrom(bmiCategory)
         Log.i("MMM", idColor.toString())
     }

     fun strBMICategory(): String {
         // TODO: Localize this!!!
         return bmiCategory.toString()
     }

     fun bmiFormatted(): String {
         return String.format(Locale.getDefault(), "%.2f", bmi)
     }
 }

// All fun in 'object' are 'static'
object Logic {
    fun calculateBMI(weight: Float, height: Float): Float {
        val heightInMeters = height / 100f
        return weight / heightInMeters.pow(2)
    }

    fun idColorFrom(bmiCategory: BMICategory): Int {
         when (bmiCategory) {
             BMICategory.UNDERWEIGHT -> return R.color.bmi_underweight
             BMICategory.NORMAL_WEIGHT -> return R.color.bmi_normal_weight
             BMICategory.OVERWEIGHT -> return R.color.bmi_overweight
             BMICategory.OBESITY -> return R.color.bmi_obesity
             BMICategory.EXTREME_OBESITY -> return R.color.bmi_extreme_obesity
        }
    }

    fun bmiCategoryFrom(bmi: Float): BMICategory {
        if (bmi < 18.5f) {  return BMICategory.UNDERWEIGHT   }
        if (bmi < 25f) {    return BMICategory.NORMAL_WEIGHT }
        if (bmi < 30f) {    return BMICategory.OVERWEIGHT    }
        if (bmi < 40f) {    return BMICategory.OBESITY       }
        return BMICategory.EXTREME_OBESITY
    }
}