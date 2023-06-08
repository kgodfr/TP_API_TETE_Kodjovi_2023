package com.apimasterucao.EgaAPI.service;

import com.apimasterucao.EgaAPI.entity.Client;
import com.apimasterucao.EgaAPI.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TETE Kodjovi
 *
 */

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<Client> showClient() {
        return clientRepository.findAll();
    }
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public Client getOneClient(Integer id) {
        return clientRepository.findById(id).get();
    }
    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}
