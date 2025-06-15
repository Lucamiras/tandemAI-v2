import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucamiras.tandemai.screens.HomeScreen
import com.lucamiras.tandemai.screens.ProfileScreen
import com.lucamiras.tandemai.screens.TestScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Create and remember the NavController

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            HomeScreen(navController = navController)
        }
        composable("testScreen") {
            TestScreen(navController = navController)
        }
        composable("ProfileScreen") {
            ProfileScreen(navController = navController)
        }
    }
}