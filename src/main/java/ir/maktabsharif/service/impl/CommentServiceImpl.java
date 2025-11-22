package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Comment;
import ir.maktabsharif.repository.CommentRepository;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.repository.impl.CommentRepositoryImpl;
import ir.maktabsharif.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    public CommentServiceImpl(CommentRepositoryImpl commentRepository) {
        super(commentRepository);
    }
}
