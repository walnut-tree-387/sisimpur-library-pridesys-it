package com.sisimpur.library.repository.predicates;

import com.sisimpur.library.model.Book;
import com.sisimpur.library.model.DeleteStatus;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookPredicateFactory {
    public static Predicate createPredicate(Map<String, Object> searchParams, Root<Book> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(getNonDeletedBookPredicate(root, cb));
        searchParams.forEach((key, value) -> {
            if (value != null && !value.toString().isEmpty()) {
                predicates.add(generatePredicate(key, value, cb, root));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
    public static Specification<Book> searchBook(Map<String, Object> searchParams) {
        return (root, query, cb) -> BookPredicateFactory.createPredicate(searchParams, root, cb);
    }
    public static Predicate generatePredicate(String key, Object value, CriteriaBuilder cb, Root<Book> root) {
        return switch (key) {
            case "bookName" ->
                    cb.like(cb.lower(root.get("title")),  value.toString().toLowerCase() + "%"); // Prefix Search
            case "authorName" -> {
                Join<Object, Object> authorJoin = root.join("author", JoinType.INNER);
                yield cb.and(
                        cb.like(
                                cb.lower(authorJoin.get("name")),
                                "%" + value.toString().toLowerCase() + "%"   // WildCard Search
                        ),
                        cb.equal(authorJoin.get("deleteStatus"), "NO")   // Excluding the deleted author from the search
                );
            }
            case "genre" ->
                    cb.equal(root.get("genre"), value); // Filter / Exact Match
            case "publishedYear" ->
                    cb.equal(root.get("publishedYear"), value); // Filter / Exact Match
            case "availableStatus" ->
                    cb.equal(root.get("availableStatus"), value); // Filter / Exact Match
            default ->
                    cb.conjunction();
        };
    }
    public static Predicate getNonDeletedBookPredicate(Root<Book> root, CriteriaBuilder cb) {
        return cb.like(root.get("deleteStatus"), "%" + DeleteStatus.NO + "%");
    }
}
