package br.com.alelo.consumer.consumerpat.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 06:29
 */

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerContactCreationException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverObjectCustomerException;
import br.com.alelo.consumer.consumerpat.respository.IRepository;
import br.com.alelo.consumer.consumerpat.util.Applog;

import java.util.List;

public abstract class AbstractBService<T> implements IService<T>{

    private final IRepository<T> repository;
    private final Class clazz;

    public AbstractBService(IRepository<T> repository, Class clazz) {
        this.repository = repository;
        this.clazz = clazz;
    }

    @Override
    public T save(T obj) {
        Applog.infoStart(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            return this.repository.save(obj);
        }catch (Exception e){
            throw new ConsumerContactCreationException();
        }finally {
            Applog.infoEnd(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    @Override
    public T recoverById(Integer value) {
        Applog.infoStart(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            return this.repository.findById(value).orElse(null);
        }catch (Exception e){
            throw new ConsumerRecoverObjectCustomerException();
        }finally {
            Applog.infoEnd(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    @Override
    public List<T> recoverAllByConsumer(Consumer consumer) {
        Applog.infoStart(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try{
            return this.repository.findAllByConsumer(consumer);
        }catch (Exception e){
            throw new ConsumerRecoverObjectCustomerException();
        }finally {
            Applog.infoEnd(this.clazz.getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

}
