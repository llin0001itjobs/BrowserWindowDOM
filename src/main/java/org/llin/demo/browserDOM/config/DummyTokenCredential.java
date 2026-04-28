package org.llin.demo.browserDOM.config;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

public class DummyTokenCredential implements TokenCredential {

    @Override
    public Mono<AccessToken> getToken(TokenRequestContext tokenRequestContext) {
        // Dummy token that never expires for local development
        return Mono.just(new AccessToken("dummy-token", OffsetDateTime.now().plusDays(365)));
    }
}