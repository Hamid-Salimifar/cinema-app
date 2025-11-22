package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Comment;
import ir.maktabsharif.repository.CommentRepository;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment> implements CommentRepository {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }
}
