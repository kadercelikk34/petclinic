package com.javaegitimleri.petclinic.service;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.dao.PetRepository;
import com.javaegitimleri.petclinic.dao.VetRepository;
import com.javaegitimleri.petclinic.exception.OwnerNotFoundException;
import com.javaegitimleri.petclinic.exception.VetNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
@Service
//butün public metodlar trn. olacak, herhangi ibr servis çağrısında trn. yoksa başlatılacak,
// başarılı veya başarısız durumuna göre  commit veya rolback olacakdır.
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImp implements PetClinicService {
    private OwnerRepository ownerRepository;
    private PetRepository petRepository;
    private VetRepository vetRepository;

    @Autowired
    public void setVetRepository(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    //@Transactional tüm özelliklerini karşılamsada kullanılabilir.readonly özlleiği yokdur.
    //@javax.transaction.Transactional(value = javax.transaction.Transactional.TxType.SUPPORTS)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null) throw new OwnerNotFoundException("Owner not found with id "+id);
        return owner;
    }

    @Override
    @CacheEvict(cacheNames = "allOwners", allEntries = true)
    public void createOwner(@Valid Owner owner) {
        ownerRepository.create(owner);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("k@s");
        msg.setTo("m@y");
        msg.setSubject("Owner created");
        msg.setText("Owner entity with id : "+ owner.getId() + "creted  successfully.");
        mailSender.send(msg);

    }

    @Override
    public void update(Owner owner) {
       ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        petRepository.deleteByOwnerId(id);
        ownerRepository.delete(id);
        //if (true) throw new RuntimeException("Testing rolback");

    }

    @Override
    public List<Vet> findVets() {
        return vetRepository.findAll();
    }

    @Override
    public Vet findVet(Long id) throws VetNotFoundException {
       return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet not foundby id: "+ id);});
    }
}
