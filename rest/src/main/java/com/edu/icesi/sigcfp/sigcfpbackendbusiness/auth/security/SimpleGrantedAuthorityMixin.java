package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class SimpleGrantedAuthorityMixin.
 */
public abstract class SimpleGrantedAuthorityMixin {

    @JsonCreator
    /**
     * Instantiates a new simple granted authority mixin.
     */
    public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {
    }


}
