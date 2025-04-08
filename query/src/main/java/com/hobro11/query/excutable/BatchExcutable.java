package com.hobro11.query.excutable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class BatchExcutable {

    public <R> List<R> execute(int batchSize, List<Long> list, Function<List<Long>, List<R>> func) {
        Set<Long> distinctList = new HashSet<>(list);
        List<R> result = new ArrayList<>();

        for (List<Long> batch : Lists.partition(new ArrayList<>(distinctList), batchSize)) {
            result.addAll(func.apply(batch));
        }

        return result;
    }

}
