package com.hobro11.command.core.client;

import java.util.Map;

public interface GeoDataClient {

    /**
     * 주소를 기반으로 Map<우편번호, 좌표>를 찾는 메서드
     * @param <T>
     * @param address
     * @param type
     * @return
     */
    <T extends Coordinate> Map<Integer, T> getCandidates(String address, Class<T> type);

}