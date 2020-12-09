#include <openssl/ssl.h>

SSL_CTX *ctx_new_01(void) {
    SSL_CTX *ctx;

    SSL_METHOD *method = DTLS_server_method();

    return SSL_CTX_new(method);
}
