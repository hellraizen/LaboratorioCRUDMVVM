package br.com.douglasmotta.mysubscribers.extension

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import br.com.douglasmotta.mysubscribers.R

private val slideLeftOption = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setEnterAnim(R.anim.slide_in_left)
    .setEnterAnim(R.anim.slide_out_left)
    .setEnterAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(
    destinationId: Int,
    animation: NavOptions = slideLeftOption
) {
    this.navigate(destinationId, null, animation)
}

fun NavController.navigateWithAnimations(
    destination: NavDirections,
    animation: NavOptions = slideLeftOption
) {
    this.navigate(destination, animation)
}