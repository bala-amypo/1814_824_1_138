package com.example.demo.service.impl;
import java.util.list;
import org.springframework.sterotype.Service;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
@Service
public class GuestServiceImpl implements GuestService{
    @Autowired
    GuestRepository lrp;
    public Guest createGuest(Guest guest){
        return lrp.save(guest);
    }
    public List


} 