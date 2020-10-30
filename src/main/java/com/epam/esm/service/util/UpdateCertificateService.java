package com.epam.esm.service.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.util.TimeManager;

@Service
public class UpdateCertificateService {

    private Map<Predicate<GiftCertificate>, BiConsumer<GiftCertificate, GiftCertificate>> giftCertificateUpdateMap;

    public UpdateCertificateService() {
        giftCertificateUpdateMap = new HashMap<>();

        giftCertificateUpdateMap.put(cert -> cert.getName() != null, (base, patch) -> base.setName(patch.getName()));
        giftCertificateUpdateMap.put(cert -> cert.getDuration() != null,
                (base, patch) -> base.setDuration(patch.getDuration()));
        giftCertificateUpdateMap.put(cert -> cert.getDescription() != null,
                (base, patch) -> base.setDescription(patch.getDescription()));
        giftCertificateUpdateMap.put(cert -> cert.getCreationTime() != null,
                (base, patch) -> base.setCreationTime(patch.getCreationTime()));
        giftCertificateUpdateMap.put(cert -> cert.getPrice() != null, (base, patch) -> base.setPrice(patch.getPrice()));
        giftCertificateUpdateMap.put(certificate -> true, (base, patch) -> base.setUpdateTime(TimeManager.now()));

    }

    public GiftCertificate updateCertificate(GiftCertificate base, GiftCertificate patch) {
        for (var test : giftCertificateUpdateMap.keySet()) {
            if (test.test(patch)) {
                giftCertificateUpdateMap.get(test).accept(base, patch);
            }
        }
        return base;

    }

}
