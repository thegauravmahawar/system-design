package urlshortenerapi.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UniqueIdService {

    //Replace with Unique ID Generator.
    public Integer getId() {
        return new Random().nextInt() & Integer.MAX_VALUE;
    }
}
