package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.PageData;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ServiceException;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class ExtractService {

    @Autowired
    private ExtractRepository extractRepository;

    @Transactional(readOnly = true)
    public PageData<Extract> getAllExtractListByPage(Pageable pageRequest) {

        Page<Extract> pageExtract = extractRepository.getAllExtractList(pageRequest);
        PageData<Extract> pageDataExtract = new PageData<>();

        pageDataExtract.setRows(pageExtract.getContent());
        pageDataExtract.setCurrentPage(pageExtract.getNumber());
        pageDataExtract.setTotalItems(pageExtract.getTotalElements());
        pageDataExtract.setTotalPages(pageExtract.getTotalPages());

        return pageDataExtract;

    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void saveExtract(Extract extract) throws ServiceException {

        try {
            extractRepository.save(extract);
        }catch (Exception e) {
            log.error("Error while saving extract:", e);
            throw new ServiceException(e);
        }

    }

    @Transactional(readOnly = true)
    public boolean existsExtractByCardNumber(long cardNumber) {
        return extractRepository.existsByCardNumber(cardNumber);
    }

    @Transactional(readOnly = true)
    public long getCountExtractDate(LocalDate date) {
        return extractRepository.getCountExtractDate(date);
    }

    @Transactional(readOnly = true)
    public List<Extract> findTop10TransactionDay(LocalDate date) {
        return extractRepository.findTop10TransactionDay(date);
    }

}