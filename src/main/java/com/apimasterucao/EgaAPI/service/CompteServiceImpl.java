package com.apimasterucao.EgaAPI.service;

import com.apimasterucao.EgaAPI.entity.Compte;
import com.apimasterucao.EgaAPI.entity.Operation;
import com.apimasterucao.EgaAPI.entity.OperationType;
import com.apimasterucao.EgaAPI.repository.CompteRepository;
import com.apimasterucao.EgaAPI.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TETE Kodjovi
 *
 */

@Service
public class CompteServiceImpl implements CompteService{
    @Autowired
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    @Override
    public List<Compte> showCompte() {
        return compteRepository.findAll();
    }

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public Compte getOneCompte(Integer id) {
        return compteRepository.findById(id).get();
    }

    @Override
    public void deleteCompte(Integer id) {
        compteRepository.deleteById(id);
    }

    @Override
    public void effectuerVersement(Integer compte_id, double montant) {
        Compte compte = getOneCompte(compte_id);
        double nouveauSolde = compte.getSolde() + montant;
        compte.setSolde(nouveauSolde);
        saveCompte(compte);
    }

    @Override
    public void effectuerRetrait(Integer compte_id, double montant) {
        Compte compte = getOneCompte(compte_id);
        double nouveauSolde = compte.getSolde() - montant;
        if (nouveauSolde >= 0) {
            compte.setSolde(nouveauSolde);
            saveCompte(compte);
        } else {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le retrait.");
        }
    }

    @Override
    public void effectuerVirement(Integer compteEmetteur_id, Integer compteBeneficiaire_id, double montant) {
        Compte compteEmetteur = getOneCompte(compteEmetteur_id);
        Compte compteBeneficiaire = getOneCompte(compteBeneficiaire_id);

        double nouveauSoldeEmetteur = compteEmetteur.getSolde() - montant;
        if (nouveauSoldeEmetteur >= 0) {
            double nouveauSoldeBeneficiaire = compteBeneficiaire.getSolde() + montant;

            compteEmetteur.setSolde(nouveauSoldeEmetteur);
            compteBeneficiaire.setSolde(nouveauSoldeBeneficiaire);

            saveCompte(compteEmetteur);
            saveCompte(compteBeneficiaire);
        } else {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le virement.");
        }
    }

    @Override
    public List<Compte> getComptesByClient(Integer client_id) {
        return compteRepository.findByClientId(client_id);
    }

    @Override
    public List<Operation> getDepotsByCompte(Integer compte_id) {
        return operationRepository.findByCompteIdAndType(compte_id, OperationType.DEPOSIT);
    }

    @Override
    public List<Operation> getRetraitsByCompte(Integer compte_id) {
        return operationRepository.findByCompteIdAndType(compte_id, OperationType.WITHDRAWAL);
    }
}
