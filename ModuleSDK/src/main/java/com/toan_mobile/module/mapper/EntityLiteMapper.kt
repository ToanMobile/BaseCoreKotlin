/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 7:49 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 7:49 PM
 *
 */

package com.toan_mobile.module.mapper

interface EntityLiteMapper<DomainModel, Entity> {

    fun mapToEntity(domain: DomainModel): Entity

    fun mapToListEntity(domainModels: List<DomainModel>): List<Entity> = domainModels.map(::mapToEntity)
}
