package com.dasoops.common.db.ktorm

import com.dasoops.common.db.ktorm.KtormGlobal.default
import org.ktorm.database.Database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.not
import org.ktorm.dsl.update
import org.ktorm.entity.*
import org.ktorm.schema.ColumnDeclaring

/**
 * Created by vince on Jun 15, 2022.
 */
abstract class BaseDao<E : DasEntity<E>, T : DasTable<E>>(private val tableObject: T) {

    fun sequenceOf(): EntitySequence<E, T> = Database.default.sequenceOf(tableObject)

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
        return sequenceOf().update(entity)
    }

    /**
     * Delete records that satisfy the given [predicate].
     */
    open fun deleteIf(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return Database.default.update(tableObject) {
            set(it.isDelete, true)
            where { predicate.invoke(it) and !it.isDelete }
        }
    }

    /**
     * 逻辑删除过滤
     */
    val logicDeleteFilter: (T) -> ColumnDeclaring<Boolean>
        get() = {
            it.isDelete eq false
        }

    /**
     * Return all entities in the table.
     */
    open fun findAll(): List<E> {
        return sequenceOf().filter(logicDeleteFilter).toList()
    }

    /**
     * Return true if all records match the given [predicate].
     */
    open fun allMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().filter(logicDeleteFilter).all(predicate)
    }

    /**
     * Return true if at least one record match the given [predicate].
     */
    open fun anyMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().filter(logicDeleteFilter).any(predicate)
    }

    /**
     * Return true if no records match the given [predicate].
     */
    open fun noneMatched(predicate: (T) -> ColumnDeclaring<Boolean>): Boolean {
        return sequenceOf().filter(logicDeleteFilter).none(predicate)
    }

    /**
     * Return the number of records in the table.
     */
    open fun count(): Int {
        return sequenceOf().filter(logicDeleteFilter).count()
    }

    /**
     * Return the number of records matching the given [predicate] in the table.
     */
    open fun count(predicate: (T) -> ColumnDeclaring<Boolean>): Int {
        return sequenceOf().filter(logicDeleteFilter).count(predicate)
    }

    /**
     * Return an entity object matching the given [predicate].
     */
    open fun findOne(predicate: (T) -> ColumnDeclaring<Boolean>): E? {
        return sequenceOf().filter(logicDeleteFilter).find(predicate)
    }

    /**
     * Return a list of entities matching the given [predicate].
     */
    open fun findList(predicate: (T) -> ColumnDeclaring<Boolean>): List<E> {
        return sequenceOf().filter(logicDeleteFilter).filter(predicate).toList()
    }
}