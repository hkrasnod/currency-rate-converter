package pl.cleankod.exchange.provider.nbp;

import pl.cleankod.exchange.provider.nbp.model.RateWrapper;

import javax.cache.Cache;
import java.util.Optional;

public class RatesCache {
    private final Cache<String, RateWrapper> innerCache;

    public RatesCache(Cache<String, RateWrapper> innerCache) {
        this.innerCache = innerCache;
    }

    public Optional<RateWrapper> tryGet(String table, String currency) {
        return Optional.ofNullable(innerCache.get(createKey(table, currency)));
    }

    public void putIfAbsent(String table, String currency, RateWrapper rate) {
        innerCache.putIfAbsent(createKey(table, currency), rate);
    }

    private String createKey(String table, String currency) {
        return String.format("%s.%s", table, currency);
    }
}
