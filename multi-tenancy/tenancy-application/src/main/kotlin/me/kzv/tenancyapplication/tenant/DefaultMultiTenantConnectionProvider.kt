package me.kzv.tenancyapplication.tenant

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl
import org.springframework.stereotype.Component
import javax.sql.DataSource

// 테넌트 정보 redis 캐싱해두고 사용하기?
// MultiTenantConnectionProvider 인터페이스를 구현하는 방법도 있다.
@Component
class DefaultMultiTenantConnectionProvider : AbstractDataSourceBasedMultiTenantConnectionProviderImpl<String>() {
    override fun selectAnyDataSource(): DataSource {
        TODO("Not yet implemented")
    }

    override fun selectDataSource(tenantCode: String): DataSource {
        TODO("Not yet implemented")
    }
}