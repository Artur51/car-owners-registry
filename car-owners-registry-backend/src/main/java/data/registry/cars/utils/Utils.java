package data.registry.cars.utils;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Utils {

    public static Sort parseSort(
            String sort) {
        if (sort == null || !sort.contains(":")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String
                    .format("Sort parameter %s is invalid", sort));
        }

        String[] params = sort.split(":");
        Sort value = Sort.by(params[0]);
        switch (params[1].toLowerCase()) {
            case "asc":
                value = value.ascending();
                break;
            case "desc":
                value = value.descending();
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String
                        .format("Unexpected sort parameter value %s; value values are: asc or desc", sort));
        }
        return value;
    }

}
