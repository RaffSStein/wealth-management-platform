package raff.stein.platformcore.security.context;

/**
 * Holds the authenticated user info in a thread-local variable.
 * Cleared at the end of each request.
 */
public class SecurityContextHolder {

    private static final ThreadLocal<WMPContext> CONTEXT = new ThreadLocal<>();

    public static void setContext(WMPContext wmpContext) {
        CONTEXT.set(wmpContext);
    }

    public static WMPContext getContext() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
