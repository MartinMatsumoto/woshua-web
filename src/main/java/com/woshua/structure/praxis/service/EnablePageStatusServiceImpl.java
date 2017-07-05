package com.woshua.structure.praxis.service;

import com.woshua.structure.praxis.domain.EnablePageStatus;
import com.woshua.structure.praxis.repository.EnablePageStatusRepository;
import com.woshua.structure.praxis.transferobj.EnablePageStatusTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/4/18.
 */
@Service("enablePageStatusService")
public class EnablePageStatusServiceImpl implements EnablePageStatusService {

    @Autowired
    private EnablePageStatusRepository enablePageStatusRepository;

    @Override
    public List<EnablePageStatusTo> findAll() {

        List<EnablePageStatus> enablePageStatuses = enablePageStatusRepository.findAll();
        List<EnablePageStatusTo> enablePageStatusTos = new ArrayList<>();

        if (enablePageStatuses != null && !enablePageStatuses.isEmpty()) {
            for (EnablePageStatus enablePageStatus : enablePageStatuses) {
                enablePageStatusTos.add(new EnablePageStatusTo(enablePageStatus));
            }

        }

        return enablePageStatusTos;
    }
}
