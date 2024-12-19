package kitten.diy.api.adapter.out.persistence.query;

import kitten.diy.api.adapter.out.model.MyLikeRankInfo;
import kitten.diy.api.adapter.out.model.MyViewRankInfo;

import java.util.List;

public interface MyPageQueryFetch {

    List<MyLikeRankInfo> getLikeCounts();

    List<MyViewRankInfo> getViewCounts();
}
