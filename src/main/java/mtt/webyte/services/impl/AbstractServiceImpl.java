package mtt.webyte.services.impl;

import mtt.webyte.dto.AbstractNonAuditDTO;
import mtt.webyte.exceptions.ResourceNotFoundException;
import mtt.webyte.mapper.AbstractMapper;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.AbstractEntity;
import mtt.webyte.services.AbstractService;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbstractServiceImpl <R extends JpaRepository, M extends AbstractMapper, D extends AbstractNonAuditDTO, E extends AbstractEntity>
        implements AbstractService<D, E>, MessageSourceAware {
    protected R repository;
    protected M mapper;
    protected D dto;
    protected E entity;
    protected MessageSource messageSource;

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public void initRepository(R repository) {
        this.repository = repository;
    }

    public void initRepository() {
        // Can override but not require
    }

    public void initMapper(M mapper) {
        this.mapper = mapper;
    }

    public void initMapper() {
        // Can override but not require
    }

    public CycleAvoidingMappingContext getCycleAvoidingMappingContext() {
        return new CycleAvoidingMappingContext();
    }

    protected D getDto() {
        if (getMapper() == null) {
            // TODO BUG new Throwable("Can not load Mapper");
            throw new RuntimeException("Can not load Mapper");
        }
        return (D) getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    protected E getEntity() {
        if (getMapper() == null) {
            // TODO BUG new Throwable("Can not load Mapper");
            throw new RuntimeException("Can not load Mapper");
        }
        return (E) getMapper().toEntity(dto, getCycleAvoidingMappingContext());
    }

    public void setDTO(D dto) {
        this.dto = dto;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    @Override
    @Transactional
    public D save(D dto) {
        // resetCycleAvoidingMappingContext();
        if (dto == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        var entity = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        entity = (AbstractEntity) getRepository().save(entity);
        return (D) getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    @Override
    public E save(E entity) {
        return null;
    }

    @Override
    public D saveAndFlush(D dto) {
        // resetCycleAvoidingMappingContext();
        if (dto == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        var entity = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        entity = (AbstractEntity) getRepository().saveAndFlush(entity);
        return (D) getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    @Override
    @Transactional
    public E saveAndFlush(E entity) {
        if (entity == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        return (E) getRepository().saveAndFlush(entity);
    }

    @Override
    public D save() {
        // resetCycleAvoidingMappingContext();
        E entity = (E) getRepository().save(getEntity());
        return (D) getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    @Override
    public List<D> save(List<D> dtos) {
        // resetCycleAvoidingMappingContext();
        if (dtos == null) {
            // TODO BUG new Throwable("Save not success.");
            throw new RuntimeException("Save not success.");
        }

        List<E> entities =
                (List<E>) dtos.stream().map(dto -> mapper.toEntity(dto, getCycleAvoidingMappingContext()))
                        .collect(Collectors.toList());
        entities = getRepository().saveAll(entities);
        return (List<D>) entities.stream()
                .map(entity -> mapper.toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public D findById(long id) {
        // resetCycleAvoidingMappingContext();
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return (D) mapper.toDto(optional.get(), getCycleAvoidingMappingContext());
    }

    @Override
    public D findById(Object key) {
        if (key instanceof Serializable) {
            // resetCycleAvoidingMappingContext();
            Optional<E> optional = getRepository().findById(key);
            if (optional.isEmpty()) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
            return (D) mapper.toDto(optional.get(), getCycleAvoidingMappingContext());
        } else {
            // TODO BUG ?
            // must be : throw new ResourceNotFoundException("Element not found.");
            return null;
        }
    }

    @Override
    public E findEntityById(Object key) {
        if (key instanceof Serializable) {
            Optional<E> optional = getRepository().findById(key);
            if (optional.isEmpty()) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
            return (E) optional.get();
        } else {
            // TODO BUG ?
            // must be : throw new ResourceNotFoundException("Element not found.");
            return null;
        }
    }

    @Override
    public void delete(long id) {
        try {
            // resetCycleAvoidingMappingContext();
            getRepository().deleteById(id);
        } catch (Exception e) {
            // TODO BUG new Throwable("Element not found.");
            throw new ResourceNotFoundException("Element not found.");
        }
    }

    @Override
    public void delete(Object key) {
        if (key instanceof Serializable) {
            try {
                // resetCycleAvoidingMappingContext();
                getRepository().deleteById(key);
            } catch (Exception e) {
                // TODO BUG new Throwable("Element not found.");
                throw new ResourceNotFoundException("Element not found.");
            }
        } else {
            // TODO BUG new Throwable("Element not found.");
            throw new ResourceNotFoundException("Element not found.");
        }
    }

    @Override
    public Pageable getPageable(Integer page, Integer size, boolean sortASC, String by) {
        Sort sortable;
        if (by != null) {
            if (sortASC) {
                sortable = Sort.by(by).ascending();
            } else {
                sortable = Sort.by(by).descending();
            }
            return PageRequest.of(page, size, sortable);
        }
        return PageRequest.of(page, size);
    }

    @Override
    public Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(page, size);
    }

    @Override
    public void delete(D dto) {
        try {
            // resetCycleAvoidingMappingContext();
            getRepository().delete(getMapper().toEntity(dto, getCycleAvoidingMappingContext()));
        } catch (Exception e) {
            // TODO BUG new Throwable("Element not found.");
            throw new ResourceNotFoundException("Element not found.");
        }
    }

    @Override
    @Transactional
    public List<D> findAll() {
        // resetCycleAvoidingMappingContext();
        List<E> list = getRepository().findAll();
        return (List<D>) list.stream()
                .map(entity -> getMapper().toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    public List<D> toListDto(List<E> lstEntity) {
        return (List<D>) lstEntity.stream()
                .map(entity -> getMapper().toDto(entity, getCycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }
}
