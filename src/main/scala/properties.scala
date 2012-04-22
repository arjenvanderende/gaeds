package com.github.hexx.gaeds

import java.util.Date
import scala.collection.JavaConverters._
import com.google.appengine.api.blobstore.BlobKey
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query.FilterOperator
import com.google.appengine.api.datastore.Query.SortDirection
import com.google.appengine.api.users.User

class BaseProperty[T: ClassManifest](var __valueOfProperty: T) {
  var __nameOfProperty: String = _
  def __valueClass = implicitly[ClassManifest[T]].erasure
  def __isOption = classOf[Option[_]].isAssignableFrom(__valueClass)
  def __isSeq = classOf[Seq[_]].isAssignableFrom(__valueClass)
  def __isSet = classOf[Set[_]].isAssignableFrom(__valueClass)
  def __isUnindexed = false
  def __javaValueOfProperty: Any = __valueOfProperty match {
    case l: Seq[_] => l.asJava
    case s: Set[_] => s.asJava
    case o: Option[_] => o getOrElse null
    case _ => __valueOfProperty
  }
  def __setToEntity(entity: Entity) = entity.setProperty(__nameOfProperty, __javaValueOfProperty)
  override def toString = __valueOfProperty.toString
}

case class Property[T: ClassManifest](__valueOfPropertyArg: T) extends BaseProperty[T](__valueOfPropertyArg)

case class UnindexedProperty[T: ClassManifest](__valueOfPropertyArg: T) extends BaseProperty[T](__valueOfPropertyArg) {
  override def __isUnindexed = true
  override def __setToEntity(entity: Entity) = entity.setUnindexedProperty(__nameOfProperty, __javaValueOfProperty)
}

case class FilterPredicate[T](val property: BaseProperty[T], val operator: FilterOperator, val value: T*)
case class SortPredicate(val property: BaseProperty[_], val direction: SortDirection)

case class PropertyOperator[T: ClassManifest](property: BaseProperty[T]) {
  def #<(v: T) = FilterPredicate(property, FilterOperator.LESS_THAN, v)
  def #<=(v: T) = FilterPredicate(property, FilterOperator.LESS_THAN_OR_EQUAL, v)
  def #==(v: T) = FilterPredicate(property, FilterOperator.EQUAL, v)
  def #!=(v: T) = FilterPredicate(property, FilterOperator.NOT_EQUAL, v)
  def #>(v: T) = FilterPredicate(property, FilterOperator.GREATER_THAN, v)
  def #>=(v: T) = FilterPredicate(property, FilterOperator.GREATER_THAN_OR_EQUAL, v)
  def in(v: T*) = FilterPredicate(property, FilterOperator.IN, v:_*)
  def asc = SortPredicate(property, SortDirection.ASCENDING)
  def desc = SortPredicate(property, SortDirection.DESCENDING)
}

object Property {
  implicit def propertyToValue[T](property: BaseProperty[T]): T = property.__valueOfProperty

  implicit def shortBlobValueToProperty(value: ShortBlob) = Property(value)
  implicit def blobValueToProperty(value: Blob) = Property(value)
  implicit def categoryValueToProperty(value: Category) = Property(value)
  implicit def booleanValueToProperty(value: Boolean) = Property(value)
  implicit def dateValueToProperty(value: Date) = Property(value)
  implicit def emailValueToProperty(value: Email) = Property(value)
  implicit def doubleValueToProperty(value: Double) = Property(value)
  implicit def geoPtValueToProperty(value: GeoPt) = Property(value)
  implicit def userValueToProperty(value: User) = Property(value)
  implicit def longValueToProperty(value: Long) = Property(value)
  implicit def blobKeyValueToProperty(value: BlobKey) = Property(value)
  implicit def keyValueToProperty(value: Key) = Property(value)
  implicit def linkValueToProperty(value: Link) = Property(value)
  implicit def imHandleValueToProperty(value: IMHandle) = Property(value)
  implicit def postalAddressValueToProperty(value: PostalAddress) = Property(value)
  implicit def ratingValueToProperty(value: Rating) = Property(value)
  implicit def phoneNumberValueToProperty(value: PhoneNumber) = Property(value)
  implicit def stringValueToProperty(value: String) = Property(value)
  implicit def textValueToProperty(value: Text) = Property(value)

  implicit def shortBlobSeqValueToProperty(value: Seq[ShortBlob]) = Property(value)
  implicit def blobSeqValueToProperty(value: Seq[Blob]) = Property(value)
  implicit def categorySeqValueToProperty(value: Seq[Category]) = Property(value)
  implicit def booleanSeqValueToProperty(value: Seq[Boolean]) = Property(value)
  implicit def dateSeqValueToProperty(value: Seq[Date]) = Property(value)
  implicit def emailSeqValueToProperty(value: Seq[Email]) = Property(value)
  implicit def doubleSeqValueToProperty(value: Seq[Double]) = Property(value)
  implicit def geoPtSeqValueToProperty(value: Seq[GeoPt]) = Property(value)
  implicit def userSeqValueToProperty(value: Seq[User]) = Property(value)
  implicit def longSeqValueToProperty(value: Seq[Long]) = Property(value)
  implicit def blobKeySeqValueToProperty(value: Seq[BlobKey]) = Property(value)
  implicit def keySeqValueToProperty(value: Seq[Key]) = Property(value)
  implicit def linkSeqValueToProperty(value: Seq[Link]) = Property(value)
  implicit def imHandleSeqValueToProperty(value: Seq[IMHandle]) = Property(value)
  implicit def postalAddressSeqValueToProperty(value: Seq[PostalAddress]) = Property(value)
  implicit def ratingSeqValueToProperty(value: Seq[Rating]) = Property(value)
  implicit def phoneNumberSeqValueToProperty(value: Seq[PhoneNumber]) = Property(value)
  implicit def stringSeqValueToProperty(value: Seq[String]) = Property(value)
  implicit def textSeqValueToProperty(value: Seq[Text]) = Property(value)

  implicit def shortBlobSetValueToProperty(value: Set[ShortBlob]) = Property(value)
  implicit def blobSetValueToProperty(value: Set[Blob]) = Property(value)
  implicit def categorySetValueToProperty(value: Set[Category]) = Property(value)
  implicit def booleanSetValueToProperty(value: Set[Boolean]) = Property(value)
  implicit def dateSetValueToProperty(value: Set[Date]) = Property(value)
  implicit def emailSetValueToProperty(value: Set[Email]) = Property(value)
  implicit def doubleSetValueToProperty(value: Set[Double]) = Property(value)
  implicit def geoPtSetValueToProperty(value: Set[GeoPt]) = Property(value)
  implicit def userSetValueToProperty(value: Set[User]) = Property(value)
  implicit def longSetValueToProperty(value: Set[Long]) = Property(value)
  implicit def blobKeySetValueToProperty(value: Set[BlobKey]) = Property(value)
  implicit def keySetValueToProperty(value: Set[Key]) = Property(value)
  implicit def linkSetValueToProperty(value: Set[Link]) = Property(value)
  implicit def imHandleSetValueToProperty(value: Set[IMHandle]) = Property(value)
  implicit def postalAddressSetValueToProperty(value: Set[PostalAddress]) = Property(value)
  implicit def ratingSetValueToProperty(value: Set[Rating]) = Property(value)
  implicit def phoneNumberSetValueToProperty(value: Set[PhoneNumber]) = Property(value)
  implicit def stringSetValueToProperty(value: Set[String]) = Property(value)
  implicit def textSetValueToProperty(value: Set[Text]) = Property(value)

  implicit def shortBlobOptionValueToProperty(value: Option[ShortBlob]) = Property(value)
  implicit def blobOptionValueToProperty(value: Option[Blob]) = Property(value)
  implicit def categoryOptionValueToProperty(value: Option[Category]) = Property(value)
  implicit def booleanOptionValueToProperty(value: Option[Boolean]) = Property(value)
  implicit def dateOptionValueToProperty(value: Option[Date]) = Property(value)
  implicit def emailOptionValueToProperty(value: Option[Email]) = Property(value)
  implicit def doubleOptionValueToProperty(value: Option[Double]) = Property(value)
  implicit def geoPtOptionValueToProperty(value: Option[GeoPt]) = Property(value)
  implicit def userOptionValueToProperty(value: Option[User]) = Property(value)
  implicit def longOptionValueToProperty(value: Option[Long]) = Property(value)
  implicit def blobKeyOptionValueToProperty(value: Option[BlobKey]) = Property(value)
  implicit def keyOptionValueToProperty(value: Option[Key]) = Property(value)
  implicit def linkOptionValueToProperty(value: Option[Link]) = Property(value)
  implicit def imHandleOptionValueToProperty(value: Option[IMHandle]) = Property(value)
  implicit def postalAddressOptionValueToProperty(value: Option[PostalAddress]) = Property(value)
  implicit def ratingOptionValueToProperty(value: Option[Rating]) = Property(value)
  implicit def phoneNumberOptionValueToProperty(value: Option[PhoneNumber]) = Property(value)
  implicit def stringOptionValueToProperty(value: Option[String]) = Property(value)
  implicit def textOptionValueToProperty(value: Option[Text]) = Property(value)

  implicit def shortBlobValueToUnindexedProperty(value: ShortBlob) = UnindexedProperty(value)
  implicit def blobValueToUnindexedProperty(value: Blob) = UnindexedProperty(value)
  implicit def categoryValueToUnindexedProperty(value: Category) = UnindexedProperty(value)
  implicit def booleanValueToUnindexedProperty(value: Boolean) = UnindexedProperty(value)
  implicit def dateValueToUnindexedProperty(value: Date) = UnindexedProperty(value)
  implicit def emailValueToUnindexedProperty(value: Email) = UnindexedProperty(value)
  implicit def doubleValueToUnindexedProperty(value: Double) = UnindexedProperty(value)
  implicit def geoPtValueToUnindexedProperty(value: GeoPt) = UnindexedProperty(value)
  implicit def userValueToUnindexedProperty(value: User) = UnindexedProperty(value)
  implicit def longValueToUnindexedProperty(value: Long) = UnindexedProperty(value)
  implicit def blobKeyValueToUnindexedProperty(value: BlobKey) = UnindexedProperty(value)
  implicit def keyValueToUnindexedProperty(value: Key) = UnindexedProperty(value)
  implicit def linkValueToUnindexedProperty(value: Link) = UnindexedProperty(value)
  implicit def imHandleValueToUnindexedProperty(value: IMHandle) = UnindexedProperty(value)
  implicit def postalAddressValueToUnindexedProperty(value: PostalAddress) = UnindexedProperty(value)
  implicit def ratingValueToUnindexedProperty(value: Rating) = UnindexedProperty(value)
  implicit def phoneNumberValueToUnindexedProperty(value: PhoneNumber) = UnindexedProperty(value)
  implicit def stringValueToUnindexedProperty(value: String) = UnindexedProperty(value)
  implicit def textValueToUnindexedProperty(value: Text) = UnindexedProperty(value)

  implicit def shortBlobSeqValueToUnindexedProperty(value: Seq[ShortBlob]) = UnindexedProperty(value)
  implicit def blobSeqValueToUnindexedProperty(value: Seq[Blob]) = UnindexedProperty(value)
  implicit def categorySeqValueToUnindexedProperty(value: Seq[Category]) = UnindexedProperty(value)
  implicit def booleanSeqValueToUnindexedProperty(value: Seq[Boolean]) = UnindexedProperty(value)
  implicit def dateSeqValueToUnindexedProperty(value: Seq[Date]) = UnindexedProperty(value)
  implicit def emailSeqValueToUnindexedProperty(value: Seq[Email]) = UnindexedProperty(value)
  implicit def doubleSeqValueToUnindexedProperty(value: Seq[Double]) = UnindexedProperty(value)
  implicit def geoPtSeqValueToUnindexedProperty(value: Seq[GeoPt]) = UnindexedProperty(value)
  implicit def userSeqValueToUnindexedProperty(value: Seq[User]) = UnindexedProperty(value)
  implicit def longSeqValueToUnindexedProperty(value: Seq[Long]) = UnindexedProperty(value)
  implicit def blobKeySeqValueToUnindexedProperty(value: Seq[BlobKey]) = UnindexedProperty(value)
  implicit def keySeqValueToUnindexedProperty(value: Seq[Key]) = UnindexedProperty(value)
  implicit def linkSeqValueToUnindexedProperty(value: Seq[Link]) = UnindexedProperty(value)
  implicit def imHandleSeqValueToUnindexedProperty(value: Seq[IMHandle]) = UnindexedProperty(value)
  implicit def postalAddressSeqValueToUnindexedProperty(value: Seq[PostalAddress]) = UnindexedProperty(value)
  implicit def ratingSeqValueToUnindexedProperty(value: Seq[Rating]) = UnindexedProperty(value)
  implicit def phoneNumberSeqValueToUnindexedProperty(value: Seq[PhoneNumber]) = UnindexedProperty(value)
  implicit def stringSeqValueToUnindexedProperty(value: Seq[String]) = UnindexedProperty(value)
  implicit def textSeqValueToUnindexedProperty(value: Seq[Text]) = UnindexedProperty(value)

  implicit def shortBlobSetValueToUnindexedProperty(value: Set[ShortBlob]) = UnindexedProperty(value)
  implicit def blobSetValueToUnindexedProperty(value: Set[Blob]) = UnindexedProperty(value)
  implicit def categorySetValueToUnindexedProperty(value: Set[Category]) = UnindexedProperty(value)
  implicit def booleanSetValueToUnindexedProperty(value: Set[Boolean]) = UnindexedProperty(value)
  implicit def dateSetValueToUnindexedProperty(value: Set[Date]) = UnindexedProperty(value)
  implicit def emailSetValueToUnindexedProperty(value: Set[Email]) = UnindexedProperty(value)
  implicit def doubleSetValueToUnindexedProperty(value: Set[Double]) = UnindexedProperty(value)
  implicit def geoPtSetValueToUnindexedProperty(value: Set[GeoPt]) = UnindexedProperty(value)
  implicit def userSetValueToUnindexedProperty(value: Set[User]) = UnindexedProperty(value)
  implicit def longSetValueToUnindexedProperty(value: Set[Long]) = UnindexedProperty(value)
  implicit def blobKeySetValueToUnindexedProperty(value: Set[BlobKey]) = UnindexedProperty(value)
  implicit def keySetValueToUnindexedProperty(value: Set[Key]) = UnindexedProperty(value)
  implicit def linkSetValueToUnindexedProperty(value: Set[Link]) = UnindexedProperty(value)
  implicit def imHandleSetValueToUnindexedProperty(value: Set[IMHandle]) = UnindexedProperty(value)
  implicit def postalAddressSetValueToUnindexedProperty(value: Set[PostalAddress]) = UnindexedProperty(value)
  implicit def ratingSetValueToUnindexedProperty(value: Set[Rating]) = UnindexedProperty(value)
  implicit def phoneNumberSetValueToUnindexedProperty(value: Set[PhoneNumber]) = UnindexedProperty(value)
  implicit def stringSetValueToUnindexedProperty(value: Set[String]) = UnindexedProperty(value)
  implicit def textSetValueToUnindexedProperty(value: Set[Text]) = UnindexedProperty(value)

  implicit def shortBlobOptionValueToUnindexedProperty(value: Option[ShortBlob]) = UnindexedProperty(value)
  implicit def blobOptionValueToUnindexedProperty(value: Option[Blob]) = UnindexedProperty(value)
  implicit def categoryOptionValueToUnindexedProperty(value: Option[Category]) = UnindexedProperty(value)
  implicit def booleanOptionValueToUnindexedProperty(value: Option[Boolean]) = UnindexedProperty(value)
  implicit def dateOptionValueToUnindexedProperty(value: Option[Date]) = UnindexedProperty(value)
  implicit def emailOptionValueToUnindexedProperty(value: Option[Email]) = UnindexedProperty(value)
  implicit def doubleOptionValueToUnindexedProperty(value: Option[Double]) = UnindexedProperty(value)
  implicit def geoPtOptionValueToUnindexedProperty(value: Option[GeoPt]) = UnindexedProperty(value)
  implicit def userOptionValueToUnindexedProperty(value: Option[User]) = UnindexedProperty(value)
  implicit def longOptionValueToUnindexedProperty(value: Option[Long]) = UnindexedProperty(value)
  implicit def blobKeyOptionValueToUnindexedProperty(value: Option[BlobKey]) = UnindexedProperty(value)
  implicit def keyOptionValueToUnindexedProperty(value: Option[Key]) = UnindexedProperty(value)
  implicit def linkOptionValueToUnindexedProperty(value: Option[Link]) = UnindexedProperty(value)
  implicit def imHandleOptionValueToUnindexedProperty(value: Option[IMHandle]) = UnindexedProperty(value)
  implicit def postalAddressOptionValueToUnindexedProperty(value: Option[PostalAddress]) = UnindexedProperty(value)
  implicit def ratingOptionValueToUnindexedProperty(value: Option[Rating]) = UnindexedProperty(value)
  implicit def phoneNumberOptionValueToUnindexedProperty(value: Option[PhoneNumber]) = UnindexedProperty(value)
  implicit def stringOptionValueToUnindexedProperty(value: Option[String]) = UnindexedProperty(value)
  implicit def textOptionValueToUnindexedProperty(value: Option[Text]) = UnindexedProperty(value)

  implicit def propertyToOperator[T: ClassManifest](property: BaseProperty[T]) = PropertyOperator(property)
}