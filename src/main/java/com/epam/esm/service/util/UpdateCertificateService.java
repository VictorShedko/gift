package com.epam.esm.service.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.util.TimeManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Service
public class UpdateCertificateService {


    private Map<Predicate<GiftCertificate>, BiConsumer<GiftCertificate, GiftCertificate>> giftCertificateUpdateMap;

    public UpdateCertificateService() {
        giftCertificateUpdateMap = new HashMap<>();

        giftCertificateUpdateMap.put(cert -> cert.getName() != null,
                (base, path) -> base.setName(path.getName()));
        giftCertificateUpdateMap.put(cert -> cert.getDuration() != null,
                (base, path) -> base.setDuration(path.getDuration()));
        giftCertificateUpdateMap.put(cert -> cert.getDescription() != null,
                (base, path) -> base.setDescription(path.getDescription()));
        giftCertificateUpdateMap.put(cert -> cert.getCreationTime() != null,
                (base, path) -> base.setCreationTime(path.getCreationTime()));
        giftCertificateUpdateMap.put(cert -> cert.getPrice() != null,
                (base, path) -> base.setPrice(path.getPrice()));
        giftCertificateUpdateMap.put(certificate -> true,
                (base,path)->base.setUpdateTime(TimeManager.now()));


    }

    public GiftCertificate updateCertificate(GiftCertificate base, GiftCertificate path) {
        for (var test:giftCertificateUpdateMap.keySet()){
            if(test.test(path)){
                giftCertificateUpdateMap.get(test).accept(base,path);
            }
        }
        return base;

    }

}
