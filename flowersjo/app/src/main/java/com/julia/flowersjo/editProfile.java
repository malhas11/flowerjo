package com.julia.flowersjo;

import android.app.Activity;

public class editProfile extends Activity {

//    ImageView searchIcon, filterIcon, backIcon, basketIcon;
//
//    TextView toolbarTitle, fname, lname, email;
//    Toolbar toolbar;
//    private static final int RC_SIGN_IN = 123;
//
//    Button signout;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile_page);
//
//
//        fname = (TextView) findViewById(R.id.fname);
//        lname = (TextView) findViewById(R.id.lname);
//        email = (TextView) findViewById(R.id.flemail);
//        signout = (Button) findViewById(R.id.signout);
//        createSignInIntent();
//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//            }
//        });
//
//    }
//
//    public void createSignInIntent() {
//        // [START auth_fui_create_intent]
//        // Choose authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build());
//
//        // Create and launch sign-in intent
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                RC_SIGN_IN);
//        // [END auth_fui_create_intent]
//    }
//
//    // [START auth_fui_result]
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//            Log.d("Tag", "Signing in");
//            if (resultCode == RESULT_OK) {
//                // Successfully signed in
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if(user != null){
//                    String name = user.getDisplayName();
//                    String Email = user.getEmail();
//                    fname.setText(name);
//                    email.setText(Email);
//                }
//
//                // ...
//            } else {
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
//            }
//        }
//    }
//    // [END auth_fui_result]





}
