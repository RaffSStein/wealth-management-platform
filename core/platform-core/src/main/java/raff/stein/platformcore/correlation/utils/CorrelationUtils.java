package raff.stein.platformcore.correlation.utils;

import org.slf4j.MDC;

import java.util.Optional;
import java.util.UUID;

public class CorrelationUtils {

    private CorrelationUtils() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String CORRELATION_ID_HEADER = "correlationId";


    public static String getCorrelationId() {
        return Optional.ofNullable(MDC.get(CORRELATION_ID_HEADER))
                .orElse(UUID.randomUUID().toString());
    }
}