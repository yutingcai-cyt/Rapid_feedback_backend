package rapidfeedback.backend.initial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.functionality.login.Dao.MarkerDao;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

/**
 * @author cyt
 * @date 2020/4/14
 */

@RestController
@RequestMapping("/v1/Marker")
public class MarkerController {

    @Autowired
    private MarkerDao markerDao;

    @GetMapping("/all")
    public List<Marker> getAll(){
        return markerDao.findAll();
    }

}
