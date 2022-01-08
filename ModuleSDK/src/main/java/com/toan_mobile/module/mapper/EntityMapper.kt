/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 7:49 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 7:49 PM
 *
 */

package com.toan_mobile.module.mapper

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domain: DomainModel): Entity

    fun mapFromListEntity(entities: List<Entity>): List<DomainModel> = entities.map(::mapFromEntity)

    fun mapToListEntity(domainModels: List<DomainModel>): List<Entity> = domainModels.map(::mapToEntity)
}
