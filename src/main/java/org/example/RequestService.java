package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    RequestRepository requestRepository;
    ExecutorRepository executorRepository;
    CustomerRepository customerRepository;

    public void createRequest(){}

    public void deleteRequest() {}
    public void changeRequestStatus() {}

//    public List<String> getRequests() {
//         var requests = requestRepository.findAll().stream().toList();
//         List<String> requestsReturn = new ArrayList<>();
//         for (Request request : requests) {
////             System.out.println(request.getId()+ ". " + request.getName() + "   Приоритет: " + request.getPriority());
//             requestsReturn.add(request.getId()+ ". " + request.getName() + "   Приоритет: " + request.getPriority());
//         }
//         return requestsReturn;
//    }



}
