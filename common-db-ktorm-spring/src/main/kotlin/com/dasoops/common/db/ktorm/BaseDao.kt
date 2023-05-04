package com.dasoops.common.db.ktorm

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.EntitySequence
import org.ktorm.entity.add
import org.ktorm.entity.all
import org.ktorm.entity.any
import org.ktorm.entity.count
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.none
import org.ktorm.entity.removeIf
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.ktorm.entity.update
import org.ktorm.schema.ColumnDeclaring
import org.ktorm.schema.Table
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by vince on Jun 15, 2022.
 */
abstract class BaseDao<E : Entity<E>, T : Table<E>>(private val tableObject: T) {
    @Autowired
    protected lateinit var database: Database

    fun sequenceOf(): EntitySequence<E, T> = database.sequenceOf(tableObject)

    /**
     * Insert the given entity into the table and return the effected record number.
     */
    open fun add(entity: E): Int {
        return sequenceOf().add(entity)
    }

    /**
     * Update properties of the given entity to the table and return the effected record number.
     */
    open fun update(entity: E): Int {
        val sequenceOf = database.sequenceOf(tableObject)
        return sequenceOf().update(entity)
    }

    /**
     * Delete records that satisfy the given [predicate].
     */
    open fun deleteIf(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return sequenceOf().removeIf(predicate)
    }

    /**
     * Return true if all records match the given [predicate].
     */
    open fun allMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().all(predicate)
    }

    /**
     * Return true if at least one record match the given [predicate].
     */
    open fun anyMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().any(predicate)
    }

    /**
     * Return true if no records match the given [predicate].
     */
    open fun noneMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().none(predicate)
    }

    /**
     * Return the number of records in the table.
     */
    open fun count(): Int {
        return sequenceOf().count()
    }

    /**
     * Return the number of records matching the given [predicate] in the table.
     */
    open fun count(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return sequenceOf().count(predicate)
    }

    /**
     * Return an entity object matching the given [predicate].
     */
    open fun findOne(predicate: (T) -> ColumnDeclaring<Boolean>): E? {
        return sequenceOf().find(predicate)
    }

    /**
     * Return a list of entities matching the given [predicate].
     */
    open fun findList(predicate: (T) -> ColumnDeclaring<Boolean>): List<E> {
        return sequenceOf().filter(predicate).toList()
    }

    /**
     * Return all entities in the table.
     */
    open fun findAll(): List<E> {
        return sequenceOf().toList()
    }
}