package com.ga.airticketmanagement.event;

import com.ga.airticketmanagement.model.User;

public record EmailPasswordResetEvent (User user, String token){}
