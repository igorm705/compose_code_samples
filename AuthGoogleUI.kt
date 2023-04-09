


 val authResultLauncherForGoogle =

        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {

                val gsa = task?.getResult(ApiException::class.java)

                if (gsa != null) {
                    coroutineScope.launch {
                        authViewModel.signIn(

                            email = gsa.email!!,
                            displayName = gsa.displayName!!,
                        )

                    }
                    //   user = authViewModel.user
                    navController.navigate("destination_meals_list")
                } else {
                    text = "Google sign in failed"
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
                Log.e("Error in AuthScreen%s", e.toString())
            }
        }

