package ge.tbc.testautomation.db.mapper;

import ge.tbc.testautomation.db.model.Branch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BranchMapper {

    @Select("SELECT latitude, longitude, max_distance_km FROM locations")
    List<Branch> getBranches();
}