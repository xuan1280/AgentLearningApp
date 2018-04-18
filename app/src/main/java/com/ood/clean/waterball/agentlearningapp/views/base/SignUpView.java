package com.ood.clean.waterball.agentlearningapp.views.base;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;

public interface SignUpView {
    void onSignUpSuccessfully(User user);
    void onAccountDuplicated();
    void onParameterInvalid();
}
