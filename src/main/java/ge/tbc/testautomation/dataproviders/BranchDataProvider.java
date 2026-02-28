package ge.tbc.testautomation.dataproviders;

import ge.tbc.testautomation.db.mapper.BranchMapper;
import ge.tbc.testautomation.db.model.Branch;
import ge.tbc.testautomation.db.session.MyBatisSessionProvider;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;

import java.util.List;

public class BranchDataProvider {

    @DataProvider(name = "locationData")
    public static Object[][] locationData() {
        try (SqlSession session = MyBatisSessionProvider.get().openSession()) {
            List<Branch> branches = session.getMapper(BranchMapper.class).getBranches();
            if (branches.isEmpty()) {
                throw new RuntimeException("No branch data returned from DB — check DB connection and locations table");
            }
            return branches.stream()
                    .map(b -> new Object[]{b.getLatitude(), b.getLongitude(), b.getMaxDistanceKm()})
                    .toArray(Object[][]::new);
        }
    }
}