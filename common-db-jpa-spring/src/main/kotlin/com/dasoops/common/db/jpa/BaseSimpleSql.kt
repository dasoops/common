package com.dasoops.common.db.jpa

import com.dasoops.common.exception.ProjectException
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.transaction.annotation.Transactional

@NoRepositoryBean
interface BaseSimpleSql<T : BaseDo> : PagingAndSortingRepository<T, Long>, CrudRepository<T, Long> {
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO")
    override fun findAll(): List<T>

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.rowId in :ids and e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO")
    fun findAll(ids: Iterable<Long>): Iterable<T>

    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.rowId = :id and e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO")
    fun findOne(id: Long): T?

    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO")
    override fun count(): Long

    @Transactional
    @Modifying
    @Query("""
        UPDATE  #{#entityName} e
        SET     e.isDelete = com.dasoops.common.db.jpa.IsDelete.YES
        WHERE   e.rowId = :id
          AND   e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO
    """)
    override fun deleteById(id: Long)

    @Transactional
    @Modifying
    override fun delete(entity: T) = this.deleteById(entity.rowId!!)

    @Transactional
    @Modifying
    @Query("""
        UPDATE  #{#entityName} e
        SET     e.isDelete = com.dasoops.common.db.jpa.IsDelete.YES
        WHERE   e.rowId IN :ids
          AND   e.isDelete = com.dasoops.common.db.jpa.IsDelete.NO
    """)
    override fun deleteAllById(ids: Iterable<Long>)

    @Transactional
    @Modifying
    override fun deleteAll(entities: Iterable<T>) = deleteAllById(entities.map { it.rowId!! })

    @Transactional
    override fun deleteAll() {
        throw ProjectException.NO_AUTH.get()
    }
}