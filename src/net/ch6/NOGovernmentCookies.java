package net.ch6;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

public class NOGovernmentCookies implements CookiePolicy {

    /*Политика использования файлов cookie, которая блокирует все файлы cookie .gov, но позволяет использовать другие*/

    @Override
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        if (uri.getAuthority().toLowerCase().endsWith(".gov")
         || cookie.getDomain().toLowerCase().endsWith(".gov")){
            return false;
        }
        return true;
    }
}
