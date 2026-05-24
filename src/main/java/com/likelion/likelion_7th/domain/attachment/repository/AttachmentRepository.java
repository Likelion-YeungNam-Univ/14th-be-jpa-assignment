package com.likelion.likelion_7th.domain.attachment.repository;

import com.likelion.likelion_7th.domain.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
