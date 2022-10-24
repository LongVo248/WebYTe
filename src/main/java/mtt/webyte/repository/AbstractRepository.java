//package mtt.webyte.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.metamodel.SingularAttribute;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Stream;
//
//public interface AbstractRepository <E , ID extends java.io.Serializable>
//        extends JpaRepository<E, ID> , AbstractRepositoryCustom<E, ID> , AbstractRepositoryHelper<E, ID> {
////    @Override
////    default java.util.Optional<E> findById(ID id) {
////        return findByIdCustom(id, FunctionUtil.getModelClassFromRepo(this.getClass().getGenericInterfaces()));
////    }
////
////    @Override
////    @SuppressWarnings("unchecked")
////    default List<E> findAll() {
////        var resultTypeClzz = FunctionUtil.getModelClassFromRepo(this.getClass().getGenericInterfaces());
////        boolean hasRemovalFlag = hasRemovalFlag(resultTypeClzz);
////        CriteriaBuilder cb =  getEntityManagerClass().getCriteriaBuilder();
////        CriteriaQuery<E> query = (CriteriaQuery<E>) cb.createQuery(resultTypeClzz);
////        Root<E> root = (Root<E>) query.from(resultTypeClzz);
////        if (hasRemovalFlag) {
////            query.where(cb.equal(root.get("removalFlag"), false));
////        }
////        return getEntityManagerClass().createQuery(query).getResultList();
////    }
////
////    @SuppressWarnings("unchecked")
////    default Optional<E> findByIdCustom(ID id, Class resultTypeClzz) {
////
////        boolean hasRemovalFlag = hasRemovalFlag(resultTypeClzz);
////        if(hasRemovalFlag) {
////            return findByIdAndRemovalFlagFalse(id, resultTypeClzz);
////        } else {
////            return (Optional<E>) Optional.ofNullable(getEntityManagerClass().find(resultTypeClzz, id));
////        }
////    }
////
////    @SuppressWarnings({"rawtypes", "unchecked"})
////    default Optional<E> findByIdAndRemovalFlagFalse(ID id,  Class resultTypeClzz) {
////        CriteriaBuilder cb =  getEntityManagerClass().getCriteriaBuilder();
////        CriteriaQuery<E> query = (CriteriaQuery<E>) cb.createQuery(resultTypeClzz);
////        Root<E> root = (Root<E>) query.from(resultTypeClzz);
////
////        var ids = getNamePrimaryId(resultTypeClzz);
////        Predicate[] predicates = new Predicate[ids.size() + 1];
////
////        predicates[0] = cb.equal(root.get("removalFlag"), CommonConstant.REMOVAL_FLAG_VALID_INT);
////
////        for (int i= 0; i < ids.size(); i++) {
////            predicates[i+1] = cb.equal(root.get(ids.get(i)), Utilities.getValue(id, ids.get(i)));
////        }
////        query.where(predicates);
////
////        return getEntityManagerClass().createQuery(query).getResultStream().findFirst();
////    }
////
////    @SuppressWarnings({"unchecked", "rawtypes"})
////    default List<String> getNamePrimaryId(Class resultTypeClzz) {
////        List<String> ids = new ArrayList<>();
////        if (getEntityManagerClass().getMetamodel().entity(resultTypeClzz).hasSingleIdAttribute()) {
////            final var idType = getEntityManagerClass().getMetamodel().entity(resultTypeClzz).getIdType().getJavaType();
////            ids.add(getEntityManagerClass().getMetamodel().entity(resultTypeClzz).getId(idType).getName());
////        } else {
////            Set<SingularAttribute> idClasses = getEntityManagerClass().getMetamodel().entity(resultTypeClzz).getIdClassAttributes();
////            for (var idClass : idClasses) {
////                ids.add(idClass.getName());
////            }
////        }
////        return  ids;
////    }
////
////    default boolean hasRemovalFlag(Class resultTypeClzz) {
////        return Stream.of(resultTypeClzz.getDeclaredFields().clone()).anyMatch(f -> "removalFlag".equalsIgnoreCase(f.getName()));
////    }
//}
