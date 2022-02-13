package com.zongjin.utils.uusn;

import com.littlenb.snowflake.sequence.IdGenerator;
import com.littlenb.snowflake.support.MillisIdGeneratorFactory;

/**
 * 基于外部雪花算法组件实现UUSN
 *
 * https://github.com/littlenb/snowflake
 * 
 * @Author Tommy
 * 
 *         2015-8-26
 */
public class LocalUUSN implements UUSN {

	// 时间初始值，用于计算时间偏移量
	private static final long epochTimestamp = 1643420959116L;

	// ID生成器
	IdGenerator idGenerator;

	public LocalUUSN() {
		MillisIdGeneratorFactory generatorFactory = new MillisIdGeneratorFactory(epochTimestamp);
		idGenerator = generatorFactory.create(183);
	}

	/**
	 * 生成UUSN
	 * 
	 * @return String
	 */
	public String generateUUSN() {
		return String.valueOf(idGenerator.nextId());
	}
}
