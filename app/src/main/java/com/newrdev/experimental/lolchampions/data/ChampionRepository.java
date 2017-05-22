package com.newrdev.experimental.lolchampions.data;

import com.newrdev.experimental.lolchampions.data.entity.Champion;
import com.newrdev.experimental.lolchampions.data.entity.ChampionsResponse;
import com.newrdev.experimental.lolchampions.data.net.Api;

import java.util.List;
import io.reactivex.Single;

/**
 * Created by rudolph on 5/18/17.
 */

public class ChampionRepository implements ChampionSource {
    private static Single<List<Champion>> champions;

    @Override
    public Single<List<Champion>> getAll() {

        if (champions == null) {
            champions = Api.Client.getInstance()
                    .getChampions()
                    .flatMapIterable(ChampionsResponse::getChampions)
                    .toList();
        }

        return champions;
    }
}
