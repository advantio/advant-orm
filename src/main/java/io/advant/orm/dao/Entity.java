package io.advant.orm.dao;

/**
 * @author Marco Romagnolo
 */
public interface Entity {

    Long getId();

    void setId(Long id);

    Long getVersion();

    void setVersion(Long version);

}
