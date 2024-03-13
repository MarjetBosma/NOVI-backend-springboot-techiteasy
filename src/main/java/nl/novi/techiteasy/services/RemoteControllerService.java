package nl.novi.techiteasy.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

private static RemoteControllerRepository rcRepos;

    public RemoteControllerService(RemoteControllerRepository rcRepos) {
        this.rcRepos = rcRepos;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {

        List<RemoteController> rcList = rcRepos.findAll();
        List<RemoteControllerDto> rcDtoList = new ArrayList<>();

        for (RemoteController rc : rcList) {
            rcDtoList.add(convertRemoteControllerToRemoteControllerDto(rc));
            }
        return rcDtoList;
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto createRemoteControllerDto){
        RemoteController rcInputDto = convertRemoteControllerDtoToRemoteController(createRemoteControllerDto);
        rcRepos.save(rcInputDto);
        return convertRemoteControllerToRemoteControllerDto(rcInputDto);
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto rcDto) {
        RemoteController rc = convertRemoteControllerDtoToRemoteController(rcDto);
        rcRepos.save(rc);
        return convertRemoteControllerToRemoteControllerDto(rc);
    }

    public void deleteRemoteController(long id) {
        Optional<RemoteController> rc = rcRepos.findById(id);
        if (rc.isPresent()) {
            rcRepos.deleteById(id);
        } else {
            throw new RecordNotFoundException("No remote controller found with id " + id);
        };
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto rcDto) {
        if (!rcRepos.existsById(id)) {
            throw new RecordNotFoundException("No remote controller found with id " + id);
        } else {
            RemoteController storedRemoteController = rcRepos.findById(id).orElse(null);
            assert storedRemoteController != null;
            storedRemoteController.setCompatibleWith(rcDto.compatibleWith);
            storedRemoteController.setBatteryType(rcDto.batteryType);
            storedRemoteController.setName(rcDto.name);
            storedRemoteController.setPrice(rcDto.price);
            storedRemoteController.setBrand(rcDto.brand);
            storedRemoteController.setOriginalStock(rcDto.originalStock);

            rcRepos.save(storedRemoteController);
        }
        return null;
    }

    public RemoteControllerDto getRemoteControllerId(long id) {
        Optional<RemoteController> remoteControllerId = rcRepos.findById(id);
        if (remoteControllerId.isPresent()) {
            RemoteController rc = remoteControllerId.get();
            return convertRemoteControllerToRemoteControllerDto(rc);
        } else {
            throw new RecordNotFoundException("No remote controller found");
        }
    }

    public RemoteControllerDto convertRemoteControllerToRemoteControllerDto(RemoteController rc) {
        RemoteControllerDto rcDto = new RemoteControllerDto();

        rcDto.id = rc.getId();
        rcDto.compatibleWith = rc.getCompatibleWith();
        rcDto.batteryType = rc.getBatteryType();
        rcDto.name = rc.getName();
        rcDto.brand = rc.getBrand();
        rcDto.price = rc.getPrice();
        rcDto.originalStock = rc.getOriginalStock();

        return rcDto;
    }

    public RemoteController convertRemoteControllerDtoToRemoteController(RemoteControllerInputDto rcDto){
        RemoteController rc = new RemoteController();

        rc.setCompatibleWith(rcDto.compatibleWith);
        rc.setBatteryType(rcDto.batteryType);
        rc.setName(rcDto.name);
        rc.setBrand(rcDto.brand);
        rc.setPrice(rcDto.price);
        rc.setOriginalStock(rcDto.originalStock);

        return rc;
    }
}
