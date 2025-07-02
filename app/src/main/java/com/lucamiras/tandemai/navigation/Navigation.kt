import android.content.Context
import android.provider.Telephony.Mms.Part
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucamiras.tandemai.screens.HomeScreen
import com.lucamiras.tandemai.screens.NewPartnerScreen
import com.lucamiras.tandemai.screens.PartnerScreen
import com.lucamiras.tandemai.screens.ProfileScreen
import com.lucamiras.tandemai.screens.TestScreen
import com.lucamiras.tandemai.viewModels.PartnerViewModel


@Composable
fun AppNavigation(appContext: Context, partnerViewModel: PartnerViewModel) {
    val navController = rememberNavController() // Create and remember the NavController

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            HomeScreen(navController = navController)
        }
        composable("testScreen") {
            TestScreen(navController = navController)
        }
        composable("profileScreen") {
            ProfileScreen(navController = navController)
        }
        composable("partnerScreen") {
            PartnerScreen(navController = navController, partnerViewModel = partnerViewModel)
        }
        composable("newPartnerScreen") {
            NewPartnerScreen(navController = navController, partnerViewModel = partnerViewModel)
        }
    }
}