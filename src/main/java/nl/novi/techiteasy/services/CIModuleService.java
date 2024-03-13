package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy.dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.CIModule;
import nl.novi.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {

    private static CIModuleRepository cimRepos;

    public CIModuleService(CIModuleRepository cimRepos) {
        this.cimRepos = cimRepos;
    }

    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModuleList = cimRepos.findAll();
        List<CIModuleDto> cmDtoList = new ArrayList<>();

        for (CIModule ci : ciModuleList) {
            cmDtoList.add(convertCIModuleToCIModuleDto(ci));
        }
        return cmDtoList;
    }

    public CIModuleDto getCIModule(long id) {
        Optional<CIModule> ciModule = cimRepos.findById(id);
        if (ciModule.isPresent()) {
            CIModuleDto ciDto = convertCIModuleToCIModuleDto(ciModule.get());
            return ciDto;
        } else {
            throw new RuntimeException("No CI-module found");
        }
    }

    public CIModuleDto createCIModule(CIModuleInputDto createCIModuleDto){
        CIModule ciInputDto = convertCIModuleDtoToCIModule(createCIModuleDto);
        cimRepos.save(ciInputDto);
        return convertCIModuleToCIModuleDto(ciInputDto);
    }
    public CIModuleDto addCIModule(CIModuleInputDto ciDto) {
        CIModule ci = convertCIModuleDtoToCIModule(ciDto);
        cimRepos.save(ci);
        return convertCIModuleToCIModuleDto(ci);
    }

    public void deleteCIModule(Long id) {
        Optional<CIModule> ci = cimRepos.findById(id);
        if (ci.isPresent()) {
            cimRepos.deleteById(id);
        } else {
            throw new RecordNotFoundException("No CI-module found with id " + id);
        }
    }

    public CIModuleDto getCIModuleId(long id) {
        Optional<CIModule> ciModuleId = cimRepos.findById(id);
        if (ciModuleId.isPresent()) {
            CIModule ci = ciModuleId.get();
            return convertCIModuleToCIModuleDto(ci);
        } else {
            throw new RecordNotFoundException("No remote controller found with id " + id);
        }
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto ciDto) {
        if (cimRepos.existsById(id)) {
            throw new RecordNotFoundException("No CI-module found");
        } else {
            CIModule storedCIModule = cimRepos.findById(id).orElse(null);
            assert storedCIModule != null;
            storedCIModule.setName(ciDto.name);
            storedCIModule.setType(ciDto.type);
            storedCIModule.setPrice(ciDto.price);

            cimRepos.save(storedCIModule);
        }
        return null;
    }

    public CIModuleDto convertCIModuleToCIModuleDto(CIModule ci) {
        CIModuleDto ciDto = new CIModuleDto();

        ciDto.id = ci.getId();
        ciDto.name = ci.getName();
        ciDto.type = ci.getType();
        ciDto.price = ci.getPrice();

        return ciDto;
    }

    public CIModule convertCIModuleDtoToCIModule(CIModuleInputDto ciDto) {
        CIModule ci = new CIModule();

        ci.setName(ciDto.name);
        ci.setType(ciDto.type);
        ci.setPrice(ciDto.price);

        return ci;
    }
}
